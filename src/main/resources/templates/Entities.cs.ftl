using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using static Luxshare.Api.Entities.Enums.CommonEnum;

namespace Luxshare.Api.Entities.Report
{
    /// <summary>
    /// 
    /// </summary>
    public class ${entityName}
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        [Key]
        [DefaultValue("newid()")]
        public Guid Guid { get; set; }

    <#list columns as column>
    <#if (column.length??) >
        [Column(TypeName = "${column.columnType}<#if (column.length != 0) >(${column.length})</#if>")]

    </#if>
        public ${column.propertyType} ${column.columnName} { get; set; }

    </#list>

        [Required]
        [Column(TypeName = "nvarchar(50)", Order = 10)]
        public string LoginName { get; set; }

        [Column(TypeName = "nvarchar(50)")]
        public string DisplayName { get; set; }

        [Column(TypeName = "nvarchar(255)")]
        public string Password { get; set; }

        [Column(TypeName = "nvarchar(255)", Order = 100)]
        public string Avatar { get; set; }

        public UserType UserType { get; set; }

        public IsLocked IsLocked { get; set; }

        //[EnumDataType(typeof(UserStatus))]
        public UserStatus Status { get; set; }

        public IsDeleted IsDeleted { get; set; }

        /// <summary>
        /// 创建时间
        /// </summary>
        public DateTime CreatedOn { get; set; }
        /// <summary>
        /// 创建者ID
        /// </summary>
        public Guid CreatedByUserGuid { get; set; }
        /// <summary>
        /// 创建者姓名
        /// </summary>
        public string CreatedByUserName { get; set; }
        /// <summary>
        /// 最近修改时间
        /// </summary>
        public DateTime? ModifiedOn { get; set; }
        /// <summary>
        /// 最近修改者ID
        /// </summary>
        public Guid? ModifiedByUserGuid { get; set; }
        /// <summary>
        /// 最近修改者姓名
        /// </summary>
        public string ModifiedByUserName { get; set; }
    }

}